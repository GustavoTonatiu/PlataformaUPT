package com.example.plataformaupt.Api.Servicios;

import com.example.plataformaupt.ViewModels.PeticionNoticia;
import com.example.plataformaupt.ViewModels.RegistroUsuario;
import com.example.plataformaupt.ViewModels.PeticionLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ServicioPeticion {
    @FormUrlEncoded
    @POST("api/crearUsuario")
    Call<RegistroUsuario> registrarUsuario(@Field("username") String correo, @Field("password") String contrasenia);

    @FormUrlEncoded
    @POST("api/login")
    Call<PeticionLogin>getLogin(@Field("username")String correo,@Field("password")String contrasenia);

    @GET("api/todasNot")
    Call<PeticionNoticia>getNoticias();

}
