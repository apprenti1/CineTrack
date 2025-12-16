package fr.hainu.cinetrack.domain.usecase.castMember

data class CastMemberUseCase (
    val getAllCastMember: GetAllCastMemberUseCase,
    val getCastMemberById: GetCastMemberByIdUseCase,
    val addCastMember: AddCastMemberUseCase,
    val updateCastMember: UpdateCastMemberUseCase,
    val removeCastMember: RemoveCastMemberUseCase,
)