package pl.szymanski.hubert.lastmodifiedrepo;

import java.net.URL;
import java.util.List;

public interface Manager {

    // get repo list - for testing
    List<Repo> getRepoList();
    void setMapper(Mapper mapper);
    Repo getLastModified();
    String getLastModifiedName();


}
