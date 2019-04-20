package pl.szymanski.hubert.lastmodifiedrepo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RepoManagerTest {

    private MockMapper mapper;
    private Manager repoManager;

    @Before
    public void setup() {
        mapper = new MockMapper();
        repoManager = new RepoManager( mapper );
    }

    @Test
    public void shouldReturnStringNull() {
        assertNull( repoManager.getLastModifiedName() );
    }

    @Test
    public void shouldReturnStringNullAtSizeZero() {
        // create list but let it be empty
        mapper.repoList = new ArrayList<>();

        assertNull( repoManager.getLastModifiedName() );
    }

    @Test
    public void shouldReturnRepoNull() {
        assertNull( repoManager.getLastModified());
    }

    @Test
    public void shouldReturnListRepoNull() {
        // try to find lastone
        repoManager.getLastModified();
        // check if it's null
        assertNull( repoManager.getRepoList() );
    }


    @Test
    public void shouldReturnFirst() {
        // add something to the list
        mapper.repoList = new ArrayList<>();
        mapper.repoList.add( new Repo("first", "2019-04-14T18:02:54Z") );
        mapper.repoList.add( new Repo("second", "2019-04-14T18:02:53Z") );
        // check what it returns
        assertEquals( "first",repoManager.getLastModifiedName() );
    }

    @Test
    public void shouldReturnSecond() {
        // add something to the list
        mapper.repoList = new ArrayList<>();
        mapper.repoList.add( new Repo("first", "2019-04-14T18:02:54Z") );
        mapper.repoList.add( new Repo("second", "2019-05-14T18:02:53Z") );
        mapper.repoList.add( new Repo("third", "2018-12-31T18:02:53Z") );
    }

    @Test
    public void shouldReturnThird() {
        // add something to the list
        mapper.repoList = new ArrayList<>();
        mapper.repoList.add( new Repo("first", "2019-04-14T18:02:54Z") );
        mapper.repoList.add( new Repo("second", "2019-04-14T18:02:53Z") );
        mapper.repoList.add( new Repo("third", "2020-04-14T18:02:53Z") );
        //
        assertEquals( "third", repoManager.getLastModifiedName() );
    }

    @Test
    public void shouldHaveSameListSize() {
        // create bigger list
        mapper.repoList = new ArrayList<>();
        for ( int i = 0; i < 234; i++) {
            mapper.repoList.add( new Repo("element", "1999-01-01T00:00:00Z") );
        }
        // get last to load list
        repoManager.getLastModified();
        // check if sizes is correct
        assertEquals(234, repoManager.getRepoList().size() );
    }

    @Test
    public void shouldReturnList() {
        // add something to the list
        List<Repo> repos = new ArrayList<>();
        repos.add( new Repo("first", "2019-04-14T18:02:54Z") );
        repos.add( new Repo("second", "2019-04-14T18:02:53Z") );
        repos.add( new Repo("third", "2020-04-14T18:02:53Z") );
        // add it to mapper
        mapper.repoList = repos;
        // get last to load list
        repoManager.getLastModified();

        // get list and compare it
        List<Repo> out = repoManager.getRepoList();
        for( int i = 0; i < out.size(); i++ ) {
            assertEquals( repos, out );
        }

    }
}