package by.tyzcorporation.library.model.repository;

import by.tyzcorporation.library.model.entity.ConcreteBook;
import by.tyzcorporation.library.model.entity.ConcreteMagazine;
import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.model.entity.type.SortDirectionType;
import by.tyzcorporation.library.model.entity.type.SortFieldType;
import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class PublicationRepositoryTest {
    private PublicationRepository repository;
    private Publication publication1;
    private Publication publication2;

    @Before
    public void setup() {
        repository = new PublicationRepository();
        publication1 = new ConcreteBook("t2", 2, "tyz2", "genre2", false);
        publication2 = new ConcreteMagazine("name", 12, "comix", "fun", false);
    }

    @Test()
    public void testAddPublication() throws NoSuchPublicationException {

        repository.addPublication(publication1);
        repository.addPublication(publication2);

        Assert.assertEquals(2, repository.size());
        Assert.assertFalse(repository.isEmpty());
        Assert.assertEquals(publication1, repository.getPublication(0));
        Assert.assertEquals(publication2, repository.getPublication(1));
    }

    @Test()
    public void testRemovePublication_Positive() throws NoSuchPublicationException {
        repository.addPublication(publication1);
        repository.addPublication(publication2);

        repository.removePublication(publication1);

        Assert.assertEquals(1, repository.size());
        Assert.assertEquals(publication2, repository.getPublication(0));
    }

    @Test
    public void testRemovePublication_Negative() {

        repository.addPublication(publication1);

        repository.removePublication(publication2);

        Assert.assertEquals(1, repository.size());
    }

    @Test()
    public void testGetPublication_Positive() throws NoSuchPublicationException {
        repository.addPublication(publication1);
        Assert.assertFalse(repository.isEmpty());
        Assert.assertEquals(publication1, repository.getPublication(0));
    }

    @Test(expected = NoSuchPublicationException.class)
    public void testGetPublication_Negative() throws NoSuchPublicationException {
        Assert.assertTrue(repository.isEmpty());
        repository.getPublication(0);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(repository.isEmpty());

        repository.addPublication(publication1);

        Assert.assertFalse(repository.isEmpty());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(0, repository.size());

        repository.addPublication(publication1);
        repository.addPublication(publication2);

        Assert.assertEquals(2, repository.size());
    }

    @Test(expected = NoSuchPublicationException.class)
    public void testClear() throws NoSuchPublicationException {
        repository.addPublication(publication1);

        repository.clear();

        Assert.assertEquals(0, repository.size());
        Assert.assertTrue(repository.isEmpty());
        Assert.assertNull(repository.getPublication(0));
    }

    @Test()
    public void testSort_Positive() throws NoSuchPublicationException {

        repository.addPublication(publication1);
        repository.addPublication(publication2);

        repository.sort(SortDirectionType.ASCENDING, SortFieldType.TITLE);

        Assert.assertEquals(publication2, repository.getPublication(0));
        Assert.assertEquals(publication1, repository.getPublication(1));

        repository.sort(SortDirectionType.ASCENDING, SortFieldType.PAGE_COUNT);

        Assert.assertEquals(publication1, repository.getPublication(0));
        Assert.assertEquals(publication2, repository.getPublication(1));
    }

    @Test()
    public void testSort_Negative() throws NoSuchPublicationException {

        repository.addPublication(publication1);
        repository.addPublication(publication2);

        repository.sort(SortDirectionType.DESCENDING, SortFieldType.TITLE);

        Assert.assertEquals(publication1, repository.getPublication(0));
        Assert.assertEquals(publication2, repository.getPublication(1));

        repository.sort(SortDirectionType.DESCENDING, SortFieldType.PAGE_COUNT);

        Assert.assertEquals(publication2, repository.getPublication(0));
        Assert.assertEquals(publication1, repository.getPublication(1));
    }

    @Test
    public void testIterator_Positive() {
        repository.addPublication(publication1);
        repository.addPublication(publication2);

        StringBuilder result = new StringBuilder();
        for (Publication publication : repository) {
            result.append(publication.getTitle()).append(", ");
        }

        String expected = "t2, name, ";
        Assert.assertEquals(expected, result.toString());
    }

    @Test(expected = NoSuchElementException.class) //don't work
    public void testIterator_Negative() {

        repository.addPublication(publication1);

        for (Publication publication : repository) {

        }
    }
}