package rs.etf.vladan.simic.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.etf.vladan.simic.domains.BaseEmail;
import rs.etf.vladan.simic.domains.VerificationEmail;

public interface NotificationRepository extends MongoRepository<BaseEmail, String> {

    public List<VerificationEmail> findBySent(boolean isSentStatus);

}
