package fr.hainu.cinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.hainu.cinetrack.data.repository.CastMemberRepositoryImpl
import fr.hainu.cinetrack.data.repository.ListRepositoryImpl
import fr.hainu.cinetrack.data.repository.MovieRepositoryImpl
import fr.hainu.cinetrack.data.repository.ReviewRepositoryImpl
import fr.hainu.cinetrack.data.repository.UserRepositoryImpl
import fr.hainu.cinetrack.domain.usecase.castMember.AddCastMemberUseCase
import fr.hainu.cinetrack.domain.usecase.castMember.CastMemberUseCase
import fr.hainu.cinetrack.domain.usecase.castMember.GetAllCastMemberUseCase
import fr.hainu.cinetrack.domain.usecase.castMember.GetCastMemberByIdUseCase
import fr.hainu.cinetrack.domain.usecase.castMember.RemoveCastMemberUseCase
import fr.hainu.cinetrack.domain.usecase.castMember.UpdateCastMemberUseCase
import fr.hainu.cinetrack.domain.usecase.list.AddListUseCase
import fr.hainu.cinetrack.domain.usecase.list.AddToMovieListUseCase
import fr.hainu.cinetrack.domain.usecase.list.GetAllListUseCase
import fr.hainu.cinetrack.domain.usecase.list.GetAllUserListUseCase
import fr.hainu.cinetrack.domain.usecase.list.GetListByIdUseCase
import fr.hainu.cinetrack.domain.usecase.list.ListUseCase
import fr.hainu.cinetrack.domain.usecase.list.RemoveListUseCase
import fr.hainu.cinetrack.domain.usecase.list.UpdateListUseCase
import fr.hainu.cinetrack.domain.usecase.movie.AddMovieUseCase
import fr.hainu.cinetrack.domain.usecase.movie.GetAllMovieUseCase
import fr.hainu.cinetrack.domain.usecase.movie.GetMovieByIdUseCase
import fr.hainu.cinetrack.domain.usecase.movie.MovieUseCase
import fr.hainu.cinetrack.domain.usecase.movie.RemoveMovieUseCase
import fr.hainu.cinetrack.domain.usecase.movie.UpdateMovieUseCase
import fr.hainu.cinetrack.domain.usecase.review.AddReviewUseCase
import fr.hainu.cinetrack.domain.usecase.review.GetAllMovieReviewUseCase
import fr.hainu.cinetrack.domain.usecase.review.GetAllReviewUseCase
import fr.hainu.cinetrack.domain.usecase.review.GetAllUserReviewUseCase
import fr.hainu.cinetrack.domain.usecase.review.GetReviewByIdUseCase
import fr.hainu.cinetrack.domain.usecase.review.RemoveReviewUseCase
import fr.hainu.cinetrack.domain.usecase.review.ReviewUseCase
import fr.hainu.cinetrack.domain.usecase.review.UpdateReviewUseCase
import fr.hainu.cinetrack.domain.usecase.user.AddUserUseCase
import fr.hainu.cinetrack.domain.usecase.user.GetAllUserMovieUseCase
import fr.hainu.cinetrack.domain.usecase.user.GetAllUserUseCase
import fr.hainu.cinetrack.domain.usecase.user.GetUserByIdUseCase
import fr.hainu.cinetrack.domain.usecase.user.RemoveUserUseCase
import fr.hainu.cinetrack.domain.usecase.user.UpdateUserUseCase
import fr.hainu.cinetrack.domain.usecase.user.UserUseCase
import fr.hainu.cinetrack.navigation.NavGraph
import fr.hainu.cinetrack.ui.theme.CineTrackTheme
import fr.hainu.cinetrack.viewModel.MovieViewModel
import fr.hainu.cinetrack.ui.viewmodels.UserViewModel
import fr.hainu.cinetrack.viewModel.CastMemberViewModel

class MainActivity : ComponentActivity() {
    private val castMemberRepository: CastMemberRepositoryImpl = CastMemberRepositoryImpl()
    private val listRepository: ListRepositoryImpl = ListRepositoryImpl()
    private val movieRepository: MovieRepositoryImpl = MovieRepositoryImpl()
    private val reviewRepository: ReviewRepositoryImpl = ReviewRepositoryImpl()
    private val userRepository: UserRepositoryImpl = UserRepositoryImpl()

    private val castMemberUseCases: CastMemberUseCase = CastMemberUseCase(
        getAllCastMember = GetAllCastMemberUseCase(castMemberRepository),
        getCastMemberById = GetCastMemberByIdUseCase(castMemberRepository),
        addCastMember = AddCastMemberUseCase(castMemberRepository),
        updateCastMember = UpdateCastMemberUseCase(castMemberRepository),
        removeCastMember = RemoveCastMemberUseCase(castMemberRepository),
    )

    private val listUseCases: ListUseCase = ListUseCase(
        getAllList = GetAllListUseCase(listRepository),
        getListById = GetListByIdUseCase(listRepository),
        getAllUserList = GetAllUserListUseCase(listRepository),
        addList = AddListUseCase(listRepository),
        addListToMovie = AddToMovieListUseCase(listRepository),
        updateList = UpdateListUseCase(listRepository),
        removeList = RemoveListUseCase(listRepository),
    )
    private val movieUseCases: MovieUseCase = MovieUseCase(
        getAllMovie = GetAllMovieUseCase(movieRepository),
        getMovieById = GetMovieByIdUseCase(movieRepository),
        addMovie = AddMovieUseCase(movieRepository),
        updateMovie = UpdateMovieUseCase(movieRepository),
        removeMovie = RemoveMovieUseCase(movieRepository),
    )
    private val reviewUseCases: ReviewUseCase = ReviewUseCase(
        getAllReview = GetAllReviewUseCase(reviewRepository),
        getReviewById = GetReviewByIdUseCase(reviewRepository),
        getAllUserReview = GetAllUserReviewUseCase(reviewRepository),
        getMovieAllReview = GetAllMovieReviewUseCase(reviewRepository),
        addReview = AddReviewUseCase(reviewRepository),
        updateReview = UpdateReviewUseCase(reviewRepository),
        removeReview = RemoveReviewUseCase(reviewRepository),
    )
    private val userUseCases: UserUseCase = UserUseCase(
        getAllUser = GetAllUserUseCase(userRepository),
        getUserById = GetUserByIdUseCase(userRepository),
        getAllUserMovie = GetAllUserMovieUseCase(userRepository),
        addUser = AddUserUseCase(userRepository),
        updateUser = UpdateUserUseCase(userRepository),
        removeUser = RemoveUserUseCase(userRepository),
    )

    private val castMemberViewModel: CastMemberViewModel =
        CastMemberViewModel(useCases = castMemberUseCases)
    //private val listViewModel: ListViewModel = ListViewModel(useCases = listUseCases)
    //private val movieViewModel: ReviewViewModel = ReviewViewModel(useCases = movieUseCases)*/
    private val reviewViewModel: MovieViewModel = MovieViewModel(useCases = movieUseCases)
    //private val userViewModel: UserViewModel = UserViewModel(useCases = userUseCases)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTrackTheme {
                NavGraph()
            }
        }
    }
}