## Create Batch Service
### 对数据库进行批量操作，使用Spring Batch

   - batch 对sample-data.csv进行数据读取，通过processor将数据写到内置数据库中
   - 通过实现ItemProcessor<Person, Person>来实现process方法，
   对Reader传入的数据进行解析，转化成writer能识别的数据格式。
   - FlatFileItemReader配置来实现对csv文件的数据解析，并且映射为Person对象
   - 通过processor对转换成输出可识别格式
   - JdbcBatchItemWriter<Person>对输出的数据进行处理，例如保存到数据库中
   
   - 而从Reader --> Processor -> Writer的这一系列过程，都是通过Job进行处理的
   - 每一个Job都有若干个step进行处理