package com.privatepocket.privatepocket.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.time.LocalTime.now;

@Service
public class RecordStorageService {

  @Autowired
  private RecordRepository recordRepository;

  public Record storeFile(String repository, MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Record record = new Record(repository, RecordType.FILE, null, java.time.LocalDateTime.now(), fileName, file.getContentType(), file.getBytes());
    return recordRepository.save(record);
  }

  public Record storeUrl(String repository, String url) throws IOException {
    Record record = new Record(repository, RecordType.URL, url, java.time.LocalDateTime.now(), null, null, null);
    return recordRepository.save(record);
  }

  public Record getRecord(String id) {
    return recordRepository.findById(id).get();
  }
  
  public List<Record> getAllRecordsByRepository(String repository) {
    return recordRepository.findByRepositoryOrderByCreateDate(repository);
  }

  public void deleteRecord(String id){
    recordRepository.deleteById(id);
  }
}
