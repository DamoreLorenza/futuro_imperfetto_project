package controller;

import entities.Desk;
import entities.User;
import exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payloads.NewDeskDTO;
import payloads.NewDeskResponseDTO;
import payloads.UserDTO;
import payloads.UserResponseDTO;
import service.DeskService;
import service.UserService;

import java.util.UUID;
@RestController
@RequestMapping("/desk")
public class DeskController {

        @Autowired
        private DeskService deskService;

        @GetMapping
        public Page<Desk> getDesk(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy){
            return deskService.getDesk(page, size, orderBy);
        }

        @GetMapping("/{id}")
        public Desk findById(@PathVariable UUID id){
            return deskService.findById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @PreAuthorize("hasAuthority('ADMIN')")
        public NewDeskResponseDTO createDesk(@RequestBody @Validated NewDeskDTO newDeskPayload, BindingResult validation){
            if (validation.hasErrors()) {
                throw new BadRequestException(validation.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList().toString());
            }
            Desk newDesk = deskService.save(newDeskPayload);
            return new NewDeskResponseDTO(newDesk.getId());

        }
        @PutMapping("/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public Desk findByIdAndUpdate(@PathVariable UUID id, @RequestBody Desk updateDeskPayload){
            return deskService.findbyIdAndUpdate(id,updateDeskPayload);
        }
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @PreAuthorize("hasAuthority('ADMIN')")
        public void findByIdAndDelete(@PathVariable UUID id){
            deskService.findByIdAndDelete(id);
        }

        //Endpoint per upload immagini
        //  @PostMapping("/{id}/upload")
        // @PreAuthorize("hasAuthority('ADMIN')")
        // public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable long id) throws IOException {
        //    return userService.uploadPicture(file);
        // }
    }

