package StSub8.Students.Courses.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * リソースが見つからない場合（404）
   */
  public static class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
      super(message);
    }
  }

  /**
   * リソースが見つからない場合のハンドリング（404）
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException e) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Not Found");
    error.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * 不正な引数（400）
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Bad Request");
    error.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  /**
   * DBエラー（500）
   */
  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<Map<String, String>> handleDataAccessError(DataAccessException e) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Database Error");
    error.put("message", "データベース操作中にエラーが発生しました");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  /**
   * その他の予期しないエラー（500）
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleOtherError(Exception e) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Internal Server Error");
    error.put("message", "予期しないエラーが発生しました");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}