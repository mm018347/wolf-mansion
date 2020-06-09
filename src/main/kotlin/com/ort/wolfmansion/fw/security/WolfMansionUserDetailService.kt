package com.ort.wolfmansion.fw.security

import com.ort.dbflute.exbhv.PlayerBhv
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service("wolfMansionUserDetailService")
class WolfMansionUserDetailService(
    val playerBhv: PlayerBhv
) : UserDetailsService {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw UsernameNotFoundException("username is empty")

        val optPlayer = playerBhv.selectEntity { it.query().setPlayerName_Equal(username) }

        return optPlayer.map {
            WolfMansionUser(
                name = it.playerName,
                pass = it.playerPassword,
                authority = it.authorityCodeAsAuthority
            )
        }.orElseThrow {
            UsernameNotFoundException("User not found for username: $username")
        }
    }
}