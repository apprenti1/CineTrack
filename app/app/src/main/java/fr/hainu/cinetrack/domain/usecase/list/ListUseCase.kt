package fr.hainu.cinetrack.domain.usecase.list

data class ListUseCase (
    val getAllList: GetAllListUseCase,
    val getAllUserList: GetAllUserListUseCase,
    val getListById: GetListByIdUseCase,
    val addList: AddListUseCase,
    val addListToMovie: AddToMovieListUseCase,
    val updateList: UpdateListUseCase,
    val removeList: RemoveListUseCase,
)