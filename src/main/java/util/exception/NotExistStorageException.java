package util.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Entity " + uuid + " not exist", uuid);
    }
}
