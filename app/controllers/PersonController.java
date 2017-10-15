package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Person;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.PersonService;

import javax.inject.Inject;

public class PersonController extends Controller {

    private final PersonService service;

    @Inject
    public PersonController(PersonService service) {
        this.service = service;
    }

    public Result getPersons() {
        return ok(Json.toJson(service.getPersons()));
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result createPerson() {
        JsonNode json = request().body().asJson();
        Person p = Json.fromJson(json, Person.class);
        if (p != null) {
            service.createPerson(p);
            return created();
        } else {
            return badRequest();
        }
    }

    public Result onOptions() {
        return ok();
    }

    public Result onOptionsWithId(Integer id) {
        return ok();
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result updatePerson() {
        JsonNode json = request().body().asJson();
        Person p = Json.fromJson(json, Person.class);
        if (p != null) {
            service.updatePerson(p);
            return created();
        } else {
            return badRequest();
        }
    }

    public Result deletePerson(Integer id) {
        service.deletePerson(id);
        return ok();
    }
}
