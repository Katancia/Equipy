package pl.karol.equipy.asssignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol.equipy.asset.Asset;
import pl.karol.equipy.asset.AssetRepository;
import pl.karol.equipy.user.User;
import pl.karol.equipy.user.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private AssetRepository assetRepository;
    private UserRepository userRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, AssetRepository assetRepository, UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Optional<Assignment> activeAssignmentForAsset = assignmentRepository
                .findByAsset_IdAndEndIsNull(assignmentDto.getAssetId());
        activeAssignmentForAsset.ifPresent((assignment -> {
            throw new InvalidAssignmentException("To wyposażenie jest aktualnie do kogoś przypisane");
        }));
        Optional<User> user = userRepository.findById(assignmentDto.getUserId());
        Optional<Asset> asset = assetRepository.findById(assignmentDto.getAssetId());
        Assignment assignment = new Assignment();
        assignment.setUser(user.orElseThrow(() ->
                new InvalidAssignmentException("Brak użytkownika z id: " + assignmentDto.getUserId())));
        assignment.setAsset(asset.orElseThrow(() ->
                new InvalidAssignmentException("Brak wyposażenia z id: " + assignmentDto.getAssetId())));
        assignment.setStart(LocalDateTime.now());
        return AssignmentMapper.toDto(assignmentRepository.save(assignment));
    }

    @Transactional
    public LocalDateTime finishAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment assignmentFound = assignment.orElseThrow(AssignmentNotFoundException::new);
        if(assignmentFound.getEnd() != null)
            throw new AssignmentAlreadyFinishedException();
        else
            assignmentFound.setEnd(LocalDateTime.now());
        return assignmentFound.getEnd();
    }
}
