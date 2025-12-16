package fr.hainu.cinetrack.domain.usecase.user

data class UserUseCase (
    val getAllUser: GetAllUserUseCase,
    val getAllUserMovie: GetAllUserMovieUseCase,
    val getUserById: GetUserByIdUseCase,
    val addUser: AddUserUseCase,
    val updateUser: UpdateUserUseCase,
    val removeUser: RemoveUserUseCase,
)