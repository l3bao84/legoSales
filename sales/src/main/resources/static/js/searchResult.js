
// Mở thanh search bar

const openOverlay = document.getElementById('myOverlay')
const search = document.getElementsByClassName('search')
const hiddenSearch = document.getElementsByClassName('hidden-search')

function showSearchBar() {
    openOverlay.style.display = 'block'
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'none';
    }

    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'flex';
    }
}

openOverlay.addEventListener('click', () => {
    openOverlay.style.display = 'none'
    openOverlay.style.zIndex = '2'
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'flex';
    }
    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'none';
    }
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    menuContent.forEach(element => {
        element.menu.style.textDecoration = "";
    })
    logo.style.positon = ''
    logo.style.zIndex = ''
    body.classList.remove('no-scroll')
})

// Real-time check email

const emailInput = document.getElementById('emailInput')
const emailStatus = document.querySelector('.email-sub-error-status')
const signal = document.querySelector('.email')

emailInput.addEventListener('input', function() {
    const email = emailInput.value;
    if (validateEmail(email)) {
        signal.style.borderColor = '#008537';
        signal.style.borderWidth = '2px'
        emailStatus.textContent = ''
    } else {
        signal.style.borderColor = '#d0021b';
        signal.style.borderWidth = '2px'
        emailStatus.textContent = 'Please enter a valid email address';
    }
  });
  
  function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  }

// Mở menu con

const hiddenMenu = document.getElementsByClassName('hidden-menu')
const btnExit = document.querySelector('.btn-exit')
const logo = document.querySelector('.header-logo')
var body = document.body

var menuContent = [
    {
        menu: document.querySelector('.shop'),
        submenu: document.querySelector('.content-1'),
    },
    {
        menu: document.querySelector('.dis'),
        submenu: document.querySelector('.content-2'),
    },
    {
        menu: document.querySelector('.help'),
        submenu: document.querySelector('.content-3'),
    },
  ];

menuContent.forEach(element => {
    element.menu.addEventListener('click', () => {

        menuContent.forEach(item => {
            item.menu.style.textDecoration = '';
            item.submenu.style.display = 'none'
        });

        openOverlay.style.display = 'block';
        for(let i = 0; i < hiddenMenu.length; i++) {
            hiddenMenu[i].style.display = 'block';
        }
        element.submenu.style.display = 'block'
        element.menu.style.textDecoration = "underline";
        logo.style.position = 'relative'
        logo.style.zIndex = '4'
        body.classList.add('no-scroll')
    });
})


btnExit.addEventListener('click', () => {
    openOverlay.style.display = 'none'
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    menuContent.forEach(element => {
        element.menu.style.textDecoration = "";
    })
    logo.style.positon = ''
    logo.style.zIndex = ''
    body.classList.remove('no-scroll')
})

const menuItems = document.querySelectorAll('.count')
const menuContentList = document.querySelector('.menu-content-list')

document.addEventListener("DOMContentLoaded", () => {
    if(menuItems.length >= 12) {
        menuContentList.style.columns = '2'
    }else {
        menuContentList.style.columns = '1'
    }
})
  
// Mở overlay đăng nhập hoặc đăng ký

const openAccount = document.querySelector('.openAccount')
const accountPanel = document.querySelector('.account-panel')
const offPanel = document.querySelector('.exit-account-modal')

openAccount.addEventListener('click', () => {
    accountPanel.style.display = 'flex'
    body.classList.add('no-scroll')
})

offPanel.addEventListener('click', () => {
    accountPanel.style.display = 'none'
    body.classList.remove('no-scroll')
})

function switchOffPanel() {
    accountPanel.style.display = 'none'
    body.classList.remove('no-scroll')
}

function preventEventPropagation(event) {
    event.stopPropagation();
}

// Mở drop down sort 

const openSort = document.querySelector('.styled-select')
const dropDown = document.querySelector('.custom-drop-down')
const dropdownItems = document.querySelectorAll('.custom-drop-down-item')
const selectedValue = document.querySelector('.selected-value')

openSort.addEventListener('click', () => {
    openSort.classList.toggle('open')
    openSort.querySelector("svg").classList.toggle('rotate-chevron')
    dropDown.classList.toggle("down")
})

dropdownItems.forEach((item) => {
    item.addEventListener('click', () => {
        selectedValue.textContent = item.querySelector("span").textContent
        openSort.classList.toggle('open')
        openSort.querySelector("svg").classList.toggle('rotate-chevron')
        dropDown.classList.toggle("down")
    })
})

// chặn hành vi của button ẩn thanh tìm kiếm

const closeButton = document.querySelector('.hidden-search-close-btn')

closeButton.addEventListener('click', (event) => {
    openOverlay.style.display = 'none'
    openOverlay.style.zIndex = '2'
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'flex';
    }
    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'none';
    }
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    event.preventDefault()
})

// kiểm tra url và đổi màu prev and next

const currentPages = []
const linkAnchors = document.querySelectorAll('.link-anchor')

linkAnchors.forEach((a) => {
    currentPages.push(parseInt(a.textContent))
})

document.addEventListener("DOMContentLoaded", () => {
    var currentUrl = window.location.href;
    var urlParams = new URLSearchParams(currentUrl)
    var pageValue = urlParams.get("page")

    if(pageValue !== null) {
        if(parseInt(pageValue) === 0) {
            document.querySelector('.pagination-prev').style.color = "rgb(117, 117, 117)"
            document.querySelector('.pagination-next').style.cursor = "pointer"
            linkAnchors[0].style.color = "rgb(0, 0, 0)"
            linkAnchors[0].style.textDecoration = "underline";
        }
        if(parseInt(pageValue) === document.querySelectorAll('.link-anchor').length - 1) {
            document.querySelector('.pagination-next').style.color = "rgb(117, 117, 117)"
            document.querySelector('.pagination-prev').style.cursor = "pointer"
        }
        currentPages.forEach((c,index) => {
            if(c === parseInt(pageValue) + 1) {
                linkAnchors[index].style.color = "rgb(0, 0, 0)"
                linkAnchors[index].style.textDecoration = "underline";
            }
        })
    }else {
        document.querySelector('.pagination-prev').style.color = "rgb(117, 117, 117)"
        document.querySelector('.pagination-next').style.cursor = "pointer"
        linkAnchors[0].style.color = "rgb(0, 0, 0)"
        linkAnchors[0].style.textDecoration = "underline";
    }
});

// next và prev page

function getCurrentPageAndKeywordFromUrl() {
    var urlParams = new URLSearchParams(window.location.search);
    
    var currentPage = parseInt(urlParams.get('page')) || 0;
    var keyword = urlParams.get('search') || '';
    
    return { currentPage: currentPage, keyword: keyword };
}
  
function direction(direction) {
    var currentParams = getCurrentPageAndKeywordFromUrl();
    var currentPage = currentParams.currentPage;
    var keyword = currentParams.keyword;
  
    if (direction === 'prev' && currentPage > 0) {
      currentPage -= 1;
    } else if (direction === 'next') {
      currentPage += 1;
    }
  
    var newUrl = `/search?search=${keyword}&page=${currentPage}`;
    window.location.href = newUrl;
}

// sắp xếp kết quả tìm kiếm

const radioButtons = document.querySelectorAll('.radio-button')

radioButtons.forEach((radio) => {
    radio.addEventListener('click', () => {
        var currentParams = getCurrentPageAndKeywordFromUrl();
        var currentPage = currentParams.currentPage;
        var keyword = currentParams.keyword;

        const selectedSortOption = radio.querySelector("span").textContent.trim()
        var newUrl = `/search?search=${keyword}&page=${currentPage}&sort=${selectedSortOption}`;
        console.log(newUrl)
        window.location.href = newUrl
    })
})

// set lựa chọn checked input radio

const selectedRadio = document.querySelectorAll('input[name="sortOption"]')

document.addEventListener("DOMContentLoaded", () => {
    const currentUrl = window.location.href;
    const urlParams = new URLSearchParams(currentUrl)
    const pageValue = urlParams.get("sort")

    

    selectedRadio.forEach((radio) => {
        if(radio.value === pageValue) {
            document.querySelector('.selected-value').textContent = radio.value
        }   
        selectedRadio.forEach((radio) => {
            radio.checked = false
        })
    })
})