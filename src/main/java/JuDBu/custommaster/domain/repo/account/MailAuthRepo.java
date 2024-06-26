package JuDBu.custommaster.domain.repo.account;

import JuDBu.custommaster.domain.entity.account.MailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  MailAuthRepo extends JpaRepository<MailAuth, Long> {
    MailAuth findTopByAccount_IdOrderBySendTimeDesc(Long id);
}
