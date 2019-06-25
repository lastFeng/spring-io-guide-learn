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
package com.springboot.uploadingfile.controller;

import com.springboot.uploadingfile.exception.StorageFileNotFoundException;
import com.springboot.uploadingfile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/25 17:09
 */
@Controller
public class UploadFileController {
	private final StorageService storageService;

	@Autowired
	public UploadFileController(StorageService storageService){
		this.storageService = storageService;
	}

	/**
	 * 文件上传主页
	 * */
	@GetMapping("/")
	public String listUploadFiles(Model model) throws IOException{
		model.addAttribute("files", storageService.loadAll()
									.map(path -> MvcUriComponentsBuilder.fromMethodName(UploadFileController.class,
								"serveFile", path.getFileName().toString()).build().toString())
									.collect(Collectors.toList()));
		return "uploadForm";
	}

	/**
	 * 文件上传主页调用该方法，在下方显示所有已上传的内容，点击可以进行下载
	 * */
	@GetMapping("/files.{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
			"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	/**
	 * 主页中，点击提交
	 * 将上传的文件保存
	 * 并进行页面消息的刷新，提示用户上传成功
	 * */
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
		storageService.store(file);

		redirectAttributes.addFlashAttribute("message",
			"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/";
	}

	/**异常捕获*/
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException e) {
		return ResponseEntity.notFound().build();
	}

}