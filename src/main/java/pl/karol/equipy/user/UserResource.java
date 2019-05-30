package pl.karol.equipy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDto> findAll(@RequestParam(required = false) String lastName) {
        return lastName != null ? userService.findAllByLastNameIgnoreCase(lastName) : userService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
        if(user.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
        UserDto savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable Long id, @RequestBody UserDto userDto) {
        if(!id.equals(userDto.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        UserDto updatedUser = userService.update(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}/assignments")
    public List<UserAssignmentDto> getUserAssignments(@PathVariable Long id) {
        return userService.getUserAssignments(id);
    }
}
