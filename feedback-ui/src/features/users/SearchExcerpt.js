

const SearchExcerpt = ({ user, selectLi }) => {

    return (
        <li data-name={user.profile.firstName + " "+ user.profile.lastName} data-id = {user.id} data-externalid= {user.externalId} onClick={ selectLi }>{user.profile.firstName} {user.profile.lastName}</li>
    )
}

export default SearchExcerpt;