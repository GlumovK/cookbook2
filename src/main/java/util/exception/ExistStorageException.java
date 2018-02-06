package util.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Entity " + uuid + " already exist", uuid);
    }
}