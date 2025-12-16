package fr.hainu.cinetrack.domain.usecase.list

data class ListUseCase (
    val getAllList: GetAllListUseCase,
    val getAllUserList: GetAllListUseCase,
    val getListById: GetListByIdUseCase,
    val addList: AddListUseCase,
    val addListToMovie: AddListUseCase,
    val updateList: UpdateListUseCase,
    val removeList: RemoveListUseCase,
)