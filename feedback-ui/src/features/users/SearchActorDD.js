import { useSelector } from "react-redux";
import { selectActorSearchUsers } from "./usersSlice";
import SearchExcerpt from "./SearchExcerpt";

const SearchActorDD = ( {selectLi} ) => {

    const searchUsers = useSelector(selectActorSearchUsers);
    const innerContent = searchUsers.map(user => <SearchExcerpt key={user.id} user={user} selectLi={selectLi}/>);

    return (
        <ul className="search-dd">
            { innerContent }
        </ul>
    )
}

export default SearchActorDD;