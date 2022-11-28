package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();
/*
    @Test
    void save() throws SQLException {

        //save
        Member member = new Member("memberV0", 10000);
        repository.save(member);
    }*/

    @Test
    void findById() throws SQLException {

        //save
        Member member = new Member("memberV0", 10000);
        repository.save(member);

        Member findMember = repository.findById("memberV0");
//        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(new Member("memberV0", 10000));
    }

    @Test
    void update() throws SQLException {

        Member member = repository.findById("memberV0");

        //update: money: 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);
    }

    @Test
    void delete() throws SQLException {

        Member member = repository.findById("memberV0");

        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}