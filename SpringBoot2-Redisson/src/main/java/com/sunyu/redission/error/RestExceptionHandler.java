package com.sunyu.redission.error;

import com.power.common.model.CommonResult;
import com.sunyu.redission.enums.ErrorCodeEnum;
import com.sunyu.redission.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * Global RestException
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult illegalParamsExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        LOGGER.error("request params invalid: {}", fieldError.getDefaultMessage());
        return processBindingError(fieldError);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String error = String.format("The parameter '%s' should be of type '%s'", ex.getName(), ex.getRequiredType().getSimpleName());
        return new CommonResult("400", error);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult noHandlerFoundException(Exception ex) {
        return new CommonResult("404", "Resource Not Found");
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CommonResult handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        List<MediaType> typeList = ex.getSupportedMediaTypes();
        for (MediaType type : typeList) {
            builder.append(type + ", ");
        }
        return new CommonResult("415", builder.toString());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public CommonResult methodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error("Error code 405: {}", ex.getMessage());
        return new CommonResult("405", ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult unknownException(Exception ex) {
        LOGGER.error("Error code 500：{}", ex);
        return new CommonResult("500", ex.getMessage());
    }


    /**
     * spring binding error
     *
     * @param fieldError
     * @return
     */
    private CommonResult processBindingError(FieldError fieldError) {
        String code = fieldError.getCode();
        LOGGER.debug("validator error code: {}", code);
        switch (code) {
            case "NotEmpty":
                return ResultUtil.error(ErrorCodeEnum.PARAM_EMPTY.getCode(), fieldError.getDefaultMessage());
            case "NotBlank":
                return ResultUtil.error(ErrorCodeEnum.PARAM_EMPTY.getCode(), fieldError.getDefaultMessage());
            case "NotNull":
                return ResultUtil.error(ErrorCodeEnum.PARAM_EMPTY.getCode(), fieldError.getDefaultMessage());
            case "Pattern":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Min":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Max":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Length":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Range":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Email":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "DecimalMin":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "DecimalMax":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Size":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Digits":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Past":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Future":
                return ResultUtil.error(ErrorCodeEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            default:
                return ResultUtil.error(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }
}
