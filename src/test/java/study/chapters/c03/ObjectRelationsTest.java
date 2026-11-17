package study.chapters.c03;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c03.ObjectRelations.*;

class ObjectRelationsTest {
    @Test void aggregationSharesExistingMembers() {
        Member member = new Member("A"); Team a = new Team(); Team b = new Team();
        a.add(member); b.add(member); a.remove(member);
        assertEquals(List.of(member), b.members()); assertSame(member, b.members().get(0));
    }
    @Test void dependencyIsProvidedAtCallTime() {
        Team team = new Team(); assertEquals("size=0", printRoster(team, s -> "size=" + team.members().size()));
        assertEquals("engine running", new Car().start());
    }
    @Test void protectsMembershipList() {
        Team team = new Team(); assertThrows(UnsupportedOperationException.class, () -> team.members().add(new Member("B")));
    }
}
