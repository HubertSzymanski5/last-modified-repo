package pl.szymanski.hubert.lastmodifiedrepo;

import java.util.List;

public interface Mapper {

    // get back full scanned list
    List<Repo> getRepoList( String urlString );

}
