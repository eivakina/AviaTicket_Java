package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket ticket1 = new Ticket("DME", "LED", 3000, 10, 12);
        Ticket ticket2 = new Ticket("DME", "LED", 5000, 14, 16);
        Ticket ticket3 = new Ticket("SVO", "AER", 3000, 11, 15);

        Assertions.assertTrue(ticket1.compareTo(ticket2) < 0);
        Assertions.assertTrue(ticket2.compareTo(ticket1) > 0);
        Assertions.assertEquals(0, ticket1.compareTo(ticket3));
    }

    @Test
    public void shouldSortSearchByPriceWhenSeveralTickets() {
        AviaSouls souls = new AviaSouls();
        Ticket t1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket t2 = new Ticket("DME", "LED", 2000, 14, 16);
        Ticket t3 = new Ticket("DME", "LED", 4000, 18, 20);

        souls.add(t1);
        souls.add(t2);
        souls.add(t3);

        Ticket[] expected = {t2, t3, t1};
        Ticket[] actual = souls.search("DME", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenOneTicket() {
        AviaSouls souls = new AviaSouls();
        Ticket t1 = new Ticket("DME", "LED", 5000, 10, 12);

        souls.add(t1);

        Ticket[] expected = {t1};
        Ticket[] actual = souls.search("DME", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenNoTicketsMatch() {
        AviaSouls souls = new AviaSouls();
        Ticket t1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket t2 = new Ticket("DME", "AER", 3000, 15, 17);

        souls.add(t1);
        souls.add(t2);

        Ticket[] expected = {};
        Ticket[] actual = souls.search("SVO", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenFromMatchesButToDoesNotMatch() {
        AviaSouls souls = new AviaSouls();
        Ticket t1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket t2 = new Ticket("DME", "AER", 3000, 15, 17);

        souls.add(t1);
        souls.add(t2);

        Ticket[] expected = {t1};
        Ticket[] actual = souls.search("DME", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareByTimeInComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("DME", "LED", 3000, 10, 11);
        Ticket t2 = new Ticket("DME", "LED", 5000, 14, 16);
        Ticket t3 = new Ticket("SVO", "AER", 4000, 18, 19);

        Assertions.assertTrue(comparator.compare(t1, t2) < 0);
        Assertions.assertTrue(comparator.compare(t2, t1) > 0);
        Assertions.assertEquals(0, comparator.compare(t1, t3));
    }

    @Test
    public void shouldSearchAndSortByTimeUsingComparator() {
        AviaSouls souls = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket t1 = new Ticket("DME", "LED", 3000, 10, 13);
        Ticket t2 = new Ticket("DME", "LED", 5000, 14, 15);
        Ticket t3 = new Ticket("DME", "LED", 4000, 18, 20);

        souls.add(t1);
        souls.add(t2);
        souls.add(t3);

        Ticket[] expected = {t2, t3, t1};
        Ticket[] actual = souls.searchAndSortBy("DME", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTimeWhenNoTicketsMatch() {
        AviaSouls souls = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket t2 = new Ticket("DME", "AER", 3000, 15, 17);

        souls.add(t1);
        souls.add(t2);

        Ticket[] expected = {};
        Ticket[] actual = souls.searchAndSortBy("SVO", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTimeWhenFromMatchesButToDoesNotMatch() {
        AviaSouls souls = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket t2 = new Ticket("DME", "AER", 3000, 15, 17);

        souls.add(t1);
        souls.add(t2);

        Ticket[] expected = {t1};
        Ticket[] actual = souls.searchAndSortBy("DME", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testTicketEqualsAndHashCode() {
        Ticket t1 = new Ticket("DME", "LED", 3000, 10, 12);
        Ticket t2 = new Ticket("DME", "LED", 3000, 10, 12);

        Ticket tDifferentFrom = new Ticket("SVO", "LED", 3000, 10, 12);
        Ticket tDifferentTo = new Ticket("DME", "AER", 3000, 10, 12);
        Ticket tDifferentPrice = new Ticket("DME", "LED", 4000, 10, 12);
        Ticket tDifferentTimeFrom = new Ticket("DME", "LED", 3000, 11, 12);
        Ticket tDifferentTimeTo = new Ticket("DME", "LED", 3000, 10, 13);

        Ticket tWithNullFrom1 = new Ticket(null, "LED", 3000, 10, 12);
        Ticket tWithNullFrom2 = new Ticket(null, "LED", 3000, 10, 12);
        Ticket tWithNullTo1 = new Ticket("DME", null, 3000, 10, 12);
        Ticket tWithNullTo2 = new Ticket("DME", null, 3000, 10, 12);

        Assertions.assertEquals(t1, t1);
        Assertions.assertEquals(t1, t2);
        Assertions.assertNotEquals(t1, null);
        Assertions.assertNotEquals(t1, "Просто строка");

        Assertions.assertNotEquals(t1, tDifferentFrom);
        Assertions.assertNotEquals(t1, tDifferentTo);
        Assertions.assertNotEquals(t1, tDifferentPrice);
        Assertions.assertNotEquals(t1, tDifferentTimeFrom);
        Assertions.assertNotEquals(t1, tDifferentTimeTo);

        Assertions.assertEquals(tWithNullFrom1, tWithNullFrom2);
        Assertions.assertNotEquals(t1, tWithNullFrom1);

        Assertions.assertEquals(tWithNullTo1, tWithNullTo2);
        Assertions.assertNotEquals(t1, tWithNullTo1);

        Assertions.assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void testFindAllAndGetters() {
        AviaSouls souls = new AviaSouls();
        Ticket t1 = new Ticket("DME", "LED", 3000, 10, 12);
        souls.add(t1);

        Ticket[] expected = {t1};
        Assertions.assertArrayEquals(expected, souls.findAll());

        Assertions.assertEquals("DME", t1.getFrom());
        Assertions.assertEquals("LED", t1.getTo());
        Assertions.assertEquals(3000, t1.getPrice());
        Assertions.assertEquals(10, t1.getTimeFrom());
        Assertions.assertEquals(12, t1.getTimeTo());
    }
}
