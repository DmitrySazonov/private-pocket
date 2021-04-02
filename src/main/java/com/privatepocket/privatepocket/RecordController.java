package com.privatepocket.privatepocket;

import com.privatepocket.privatepocket.storage.Record;
import com.privatepocket.privatepocket.storage.RecordStorageService;
import com.privatepocket.privatepocket.web.RecordUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private RecordStorageService recordStorageService;

    @Autowired
    public RecordController(RecordStorageService recordStorageService) {
        this.recordStorageService = recordStorageService;
    }

    @GetMapping("/")
    public List<Record> listOfRecords() throws IOException {
        List<Record> records = recordStorageService.getAllRecordsByRepository("test");
        return records;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    Record newUrl(@RequestBody RecordUrl url) throws IOException {
        return recordStorageService.storeUrl("test", url.getUrl());
    }

}
