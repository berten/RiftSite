package org.deschutter.parser.exception;

/**
 *
 * @author berten
 */
public class FileNullException extends RuntimeException {

    public FileNullException() {
        super("File name should be set");
    }
}