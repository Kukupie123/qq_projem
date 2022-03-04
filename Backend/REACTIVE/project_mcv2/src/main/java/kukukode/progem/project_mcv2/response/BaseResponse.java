package kukukode.progem.project_mcv2.response;

//We are using interfaces to add a layer of abstraction so that in future we can easily change the workings
public interface BaseResponse<T> {
    void setData(T data);

    void setMessage(String message);

    void setDataAndMessage(T data, String message);

    T getData();

    String getMessage();
}
