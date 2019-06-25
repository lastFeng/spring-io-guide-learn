/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.uploadingfile.service.impl;

import com.springboot.uploadingfile.exception.StorageException;
import com.springboot.uploadingfile.exception.StorageFileNotFoundException;
import com.springboot.uploadingfile.property.StorageProperties;
import com.springboot.uploadingfile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/25 17:12
 *
 * 对文件上传的逻辑处理
 */
@Service
public class StorageServiceImpl implements StorageService {

	/**读取当前文件夹路径，/upload-dir*/
	private final Path rootLocation;

	@Autowired
	public StorageServiceImpl(StorageProperties properties){
		this.rootLocation = Paths.get(properties.getLocation());
	}

	/**
	 * 初始化文件夹，如果不存在，创建
	 * */
	@Override
	public void init() {
		try {
			if (!Files.exists(rootLocation)) {
				Files.createDirectory(rootLocation);
			}
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	/**
	 * 保存传入的文件
	 * */
	@Override
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));

		} catch (IOException e) {
			throw new StorageException("Failed to store file: " + file.getOriginalFilename());
		}
	}

	/**
	 * 加载/upload-dir文件夹下的所有文件信息
	 * */
	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(rootLocation))
				.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files ", e);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	/**将文件以资源的形式在网页中展示
	 * */
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);

			Resource resource = new UrlResource(file.toUri());

			// 文件只有存在或可读才能加载
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename);
		}
	}

	/**
	 * 删除/upload-dir文件夹下的所有文件
	 * */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
}