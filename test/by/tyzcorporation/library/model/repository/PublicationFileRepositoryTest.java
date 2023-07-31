package by.tyzcorporation.library.model.repository;

import by.tyzcorporation.library.model.entity.ConcreteAlbum;
import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.model.entity.type.SortDirectionType;
import by.tyzcorporation.library.model.entity.type.SortFieldType;
import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PublicationFileRepositoryTest {
    private PublicationFileRepository repository;
    private Publication publication1;
    private Publication publication2;

    @Before
    public void setup() {
        repository = new PublicationFileRepository();
        publication1 = new ConcreteAlbum(1,"tsdf", 2);
        publication2 = new ConcreteAlbum(2, "name", 12);
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

        repository.removePublication(1);

        Assert.assertEquals(publication2, repository.getPublication(0));
    }

    @Test()
    public void testRemovePublication_Negative() {

        repository.addPublication(publication1);

        repository.removePublication(2);

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

        Assert.assertEquals(publication2, repository.findPublicationById(1));
        Assert.assertEquals(publication1, repository.findPublicationById(2));

        repository.sort(SortDirectionType.ASCENDING, SortFieldType.PAGE_COUNT);

        Assert.assertEquals(publication1, repository.findPublicationById(1));
        Assert.assertEquals(publication2, repository.findPublicationById(2));
    }

    @Test()
    public void testSort_Negative() throws NoSuchPublicationException {

        repository.addPublication(publication1);
        repository.addPublication(publication2);

        repository.sort(SortDirectionType.DESCENDING, SortFieldType.TITLE);

        Assert.assertEquals(publication2, repository.findPublicationById(1));
        Assert.assertEquals(publication1, repository.findPublicationById(2));

        repository.sort(SortDirectionType.DESCENDING, SortFieldType.PAGE_COUNT);

        Assert.assertEquals(publication1, repository.findPublicationById(1));
        Assert.assertEquals(publication2, repository.findPublicationById(2));
    }

}