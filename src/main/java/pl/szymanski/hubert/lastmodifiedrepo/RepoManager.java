package pl.szymanski.hubert.lastmodifiedrepo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepoManager implements Manager {

    //--- FIELDS ---//

    private List<Repo> repoList;
    private String repoUrlString;
    private Mapper mapper;


    //--- CONSTRUCTORS ---//

    public RepoManager() {
        this.repoUrlString = "https://api.github.com/users/allegro/repos";
        this.mapper = new GitHubMapper();
    }

    // for testing
    public RepoManager( Mapper mapper ) {
        this();
        this.mapper = mapper;
    }


    //--- GETTERS AND SETTERS ---//

    // debug helper
    @Override
    public List<Repo> getRepoList() {

        return repoList;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


    //--- INTERFACE METHODS ---//

    @Override
    public Repo getLastModified() {
        // get list from mapper
        repoList = mapper.getRepoList( repoUrlString );

        // check if there is anything to work with
        if ( repoList == null || repoList.size() == 0) return null;

        // declare lastRepo to return
        Repo lastRepo;

        // set it as the first element of the list
        lastRepo = repoList.get(0);

        int comparator;
        // now iterate through the list and find the last modified
        for ( var repoItem : repoList ) {
            // since date is in ISO 8601 format I can easly compare strings
            // if comparator is less than 0 then lastRepo is older and we need to swap it
            comparator = lastRepo.getUpdated_at().compareTo(repoItem.getUpdated_at());
            if ( comparator < 0 ) lastRepo = repoItem;
        }

        return lastRepo;
    }

    @Override
    public String getLastModifiedName() {
        Repo lastMod = getLastModified();
        return (lastMod == null) ? null : lastMod.getName();
    }
}
