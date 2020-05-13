package yardani.domain;

public class ErrorMessage {

    private String errorMessage;
    private int errorCode;

    public ErrorMessage(String errorMessage, int errorCode) {
        setErrorMessage(errorMessage);
        setErrorCode(errorCode);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
