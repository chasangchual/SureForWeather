package com.surefor.weather.event;

/**
 * Created by Ethan on 2016-02-04.
 */
public abstract class GetWeatherEvent<T> {
    public GetWeatherEvent() {
    }

    public Request getRequest(Long id) {
        return new Request(id) ;
    }

    public Request getRequest(String name) {
        return new Request(name) ;
    }

    public Response getResponse(T obj) {
        return new Response(obj) ;
    }

    public enum REQUEST_TYPE { BY_ID, BY_NAME }

    public static class Request {
        private REQUEST_TYPE requestType ;
        private Long id ;
        private String name ;

        public Request(long id) {
            setId(id);
        }

        public Request(String name) {
            setName(name);
        }

        public REQUEST_TYPE getRequestType() {
            return requestType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.requestType = REQUEST_TYPE.BY_ID ;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.requestType = REQUEST_TYPE.BY_NAME ;
            this.name = name;
        }
    }

    public static class Response<T> {
        private T obj ;

        public Response(T t) {
            this.obj = t ;
        }

        public Object getObject() {
            return obj;
        }

        public void setObject(T obj) {
            this.obj = obj;
        }
    }

}
