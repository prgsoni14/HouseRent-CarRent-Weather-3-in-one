package com.production.hrow.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.production.hrow.helper.JwtRequest;
import com.production.hrow.helper.JwtUtil;
import com.production.hrow.models.User;
import com.production.hrow.services.UserService;
import com.production.hrow.services.impl.ImageServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ImageServiceImpl imageService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestPart("user") User user, @RequestParam("profilePic") MultipartFile profilePic)
    {
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProfilePic(imageService.uploadImage(profilePic, user.getUsername()));

        User new_user = userService.createUser(user);
        return ResponseEntity.ok().body(new_user);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody JwtRequest jwtRequest)
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Credentials"); 
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        return ResponseEntity.ok().body(jwtUtil.generateToken(userDetails));
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> currentUser(Principal principal)
    {
        Map<String, Object> responseMap = new HashMap<>();
        User user = userService.getUser(principal.getName());
        responseMap.put("user", user);
        responseMap.put("profilePic", imageService.downloadImage(user.getProfilePic().getId()));
        return ResponseEntity.ok().body(responseMap);
    }
    

    @DeleteMapping("/delete/{username}/{id}")
    @PreAuthorize("authentication.principal.username == #username")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id, @PathVariable("username") String username)
    {
        return ResponseEntity.ok().body(userService.deleteUser(id, username));
    }

    @PutMapping("/update/{username}/{id}")
    @PreAuthorize("authentication.principal.username == #username")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id, @PathVariable("username") String username) {
        System.out.println(user);
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userService.updateUser(user, username,id));
    }
}
