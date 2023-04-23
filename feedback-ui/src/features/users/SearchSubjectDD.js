import { searchUsers } from "./usersSlice";
import { useSelector } from "react-redux";
import { selectSubjectSearchUsers } from "./usersSlice";
import SearchExcerpt from "./SearchExcerpt";

const SearchSubjectDD = ( {selectLi} ) => {

    const searchUsers = useSelector(selectSubjectSearchUsers);
    const innerContent = searchUsers.map(user => <SearchExcerpt key={user.id} user={user} selectLi={selectLi}/>);

    return (
        <ul className="search-dd">
            { innerContent }
        </ul>
    )
}

export default SearchSubjectDD;