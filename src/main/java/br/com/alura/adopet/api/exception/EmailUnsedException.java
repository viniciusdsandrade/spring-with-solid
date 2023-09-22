package br.com.alura.adopet.api.exception;

public class EmailUnsedException extends RuntimeException {
    
        public EmailUnsedException(String message) {
            super(message);
        }
}
