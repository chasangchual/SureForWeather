package com.surefor.weather.event;

/**
 * Created by Ethan on 2016-02-04.
 */
public abstract class GetWeatherEvent<T> {
    public GetWeatherEvent() {
    }

    public Request getRequest(String name) {
        return new Request(name) ;
    }

    public Response getResponse(String name, T obj) {
        return new Response(name, obj) ;
    }

    public static class Request {
        private String name ;

        public Request(String name) {
            setName(name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Response<T> {
        private T obj ;
        private String name ;

        public Response(String name, T t) {
            this.name = name ;
            this.obj = t ;
        }

        public T getObject() {
            return obj;
        }

        public void setObject(T obj) {
            this.obj = obj;
        }

        public String getName() {
            return this.name ;
        }
    }
}
