
/*chan hanh vi mac dinh*/

// sign up
function createAcc(event) {
    event.preventDefault()
    window.location.href = '/signup'
}

function socialLogin(event) {
    event.preventDefault()
}

/*hien thi mat khau*/

const showPass = document.querySelector(".show-password")

showPass.addEventListener("click", () => {
    const passwordInput = document.getElementById('password')
    const icon = document.querySelector(".eye-icon")
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        icon.innerHTML = '<svg width="21" height="21" viewBox="0 0 21 21" fill="none" xmlns="http://www.w3.org/2000/svg" class="hide-pass-icon"><circle cx="10.5" cy="10.3" r="3.5" fill="currentColor"></circle><path d="M10.5 15.5501C7.2757 15.5501 3.9457 13.8688 1.28963 10.3C3.9457 6.73129 7.2757 5.05 10.5 5.05C13.7243 5.05 17.0543 6.73129 19.7104 10.3C17.0543 13.8688 13.7243 15.5501 10.5 15.5501Z" stroke="currentColor" stroke-width="2.1"></path></svg>'
    } else {
        passwordInput.type = 'password';
        icon.innerHTML = '<svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" class="show-pass-icon"><circle cx="16" cy="16" r="5" fill="currentColor"></circle><path d="M16 23.5001C11.3939 23.5001 6.63672 21.0982 2.84233 16C6.63672 10.9018 11.3939 8.5 16 8.5C20.6061 8.5 25.3633 10.9018 29.1577 16C25.3633 21.0982 20.6061 23.5001 16 23.5001Z" stroke="currentColor" stroke-width="3"></path><line x1="3" y1="28.8787" x2="26.8787" y2="5" stroke="currentColor" stroke-width="3" stroke-linecap="round"></line></svg>'
    }
})

/*back to home*/

const back = document.querySelector('.back-to-home')

back.addEventListener("click", () => {
    window.location.href = '/home'
})
