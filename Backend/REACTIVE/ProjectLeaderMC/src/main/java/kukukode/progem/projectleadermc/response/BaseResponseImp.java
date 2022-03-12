package kukukode.progem.projectleadermc.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseImp<SomeType> implements BaseResponse<SomeType> {
    //Data can be of any type, eg string, json, POJO
    SomeType data;
    String message;

    @Override
    public void setData(SomeType data) {
        this.data = data;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setDataAndMessage(SomeType data, String message) {

    }

    @Override
    public SomeType getData() {
        return this.data;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
