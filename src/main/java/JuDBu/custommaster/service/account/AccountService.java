package JuDBu.custommaster.service.account;


import JuDBu.custommaster.dto.account.AccountDto;
import JuDBu.custommaster.entity.account.Account;
import JuDBu.custommaster.repo.account.AccountRepo;
import JuDBu.custommaster.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;
    private final AuthenticationFacade authFacade;

    // 유저 정보 가져오기
    public AccountDto readOneAccount() {
        Account account = authFacade.getAccount();

        log.info("auth user: {}", authFacade.getAuth().getName());
        log.info("page username: {}", account.getUsername());

        // 토큰으로 접근 시도한 유저와, 페이지의 유저가 다른경우 예외
        if (!authFacade.getAuth().getName().equals(account.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return AccountDto.fromEntity(account);
    }

    public AccountDto readOneAccount(Long id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return AccountDto.fromEntity(account);
    }

    // 사용자 정보 수정
    public AccountDto updateAccount(AccountDto dto) {
        Account target = authFacade.getAccount();

        log.info("auth user: {}", authFacade.getAuth().getName());
        log.info("page username: {}", target.getUsername());

        // 토큰으로 접근 시도한 유저와, 페이지의 유저가 다른경우 예외
        if (!authFacade.getAuth().getName().equals(target.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        target.updateInfo(dto.getPassword(), dto.getName(), dto.getEmail());

        return AccountDto.fromEntity(accountRepo.save(target));
    }

}