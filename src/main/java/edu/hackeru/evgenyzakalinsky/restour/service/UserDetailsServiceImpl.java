package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.SignUpRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.SignUpResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.entity.User;
import edu.hackeru.evgenyzakalinsky.restour.error.BadRequestException;
import edu.hackeru.evgenyzakalinsky.restour.error.PackageException;
import edu.hackeru.evgenyzakalinsky.restour.repository.CommentRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.RoleRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto dto) {

        val userRole = roleRepository
                .findByRoleNameIgnoreCase("ROLE_USER")
                .orElseThrow(() -> new PackageException("Please contact ADMIN"));

        val userEmail = userRepository
                .findByEmailIgnoreCase(dto.getEmail().trim());
        if(userEmail.isPresent()) {
            throw new BadRequestException("email", "Email already exists");
        }

        var user = new User(
                null,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDob(),
                dto.getPhone(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword().trim()),
                List.of(),
                Set.of(userRole)
        );

        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, SignUpResponseDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        var roles = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), roles
        );
    }

    public SignUpResponseDto deleteUser(long id) {

        Optional<User> deletedUser = userRepository.findById(id);
        userRepository.deleteById(id);
        commentRepository.findCommentsByUserId(id);
        return modelMapper.map(deletedUser, SignUpResponseDto.class);
    }
}
