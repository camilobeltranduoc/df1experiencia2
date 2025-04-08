package com.duoc.peliculas.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "status", "count", "timestamp", "data" })
public class ResponseWrapper<T> {
    
    private String status;      // Por ejemplo, "OK" o "ERROR"
    private int count;          // Cantidad de elementos (por ejemplo, tamaño de la lista o 1 para un objeto)
    private LocalDateTime timestamp;
    private T data;             // El dato envuelto

    // Constructor
    public ResponseWrapper(String status, int count, T data) {
        this.status = status;
        this.count = count;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y setters 
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
