package com.iljun.hateoasexample.domains.user.dto;

import com.iljun.hateoasexample.api.UserApi;
import com.iljun.hateoasexample.domains.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return new EntityModel<User>(
                user,
                linkTo(methodOn(UserApi.class).getUser(user.getId())).withRel("get"),
                linkTo(methodOn(UserApi.class).getUsers(PageRequest.of(0,10))).withRel("all"));
    }
}
