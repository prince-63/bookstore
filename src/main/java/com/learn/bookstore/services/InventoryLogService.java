package com.learn.bookstore.services;

import com.learn.bookstore.models.InventoryLog;

import java.util.List;

public interface InventoryLogService {

    void logStockChange(Long bookId, String action, int quantity, String reason);

    List<InventoryLog> getLogsByBook(Long bookId);

}

