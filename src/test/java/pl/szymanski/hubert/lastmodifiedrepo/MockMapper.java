package pl.szymanski.hubert.lastmodifiedrepo;

import java.util.List;

public class MockMapper implements Mapper {

    public List<Repo> repoList;

    public MockMapper() {

    }


    @Override
    public List<Repo> getRepoList(String urlString) {
        return repoList;
    }
}
