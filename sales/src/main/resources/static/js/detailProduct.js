
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

// Next và Prev các sản phẩm được gợi ý

const products = document.querySelectorAll('.product-item')
const nextBtn = document.querySelector('.next')
const prevBtn = document.querySelector('.prev')

let currentIndex = 0;
const itemsPerPage = 4;

function showProducts(index) {

    products.forEach((product) => {
        product.classList.remove('active');
    });
  
    for (let i = index; i < index + itemsPerPage; i++) {
        if (products[i]) {
          products[i].classList.add('active');
        }
    }

    index == products.length - 4
    ? nextBtn.classList.add("hide")
    : nextBtn.classList.remove("hide");

    index == 0
    ? prevBtn.classList.add("hide")
    : prevBtn.classList.remove("hide");

}

prevBtn.addEventListener('click', () => {
    currentIndex -= itemsPerPage;
    if (currentIndex < 0) {
      currentIndex = 0;
    }
    showProducts(currentIndex);
});
  
nextBtn.addEventListener('click', () => {
    currentIndex += itemsPerPage;
    if (currentIndex >= products.length) {
      currentIndex = products.length - itemsPerPage;
    }
    showProducts(currentIndex);
});
  
showProducts(currentIndex);

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

const accountPanel = document.querySelector('.account-panel')
const offPanel = document.querySelector('.exit-account-modal')

document.addEventListener("DOMContentLoaded", () => {
    const account = document.querySelector('.openAccount')
    if(account !== null) {
        account.addEventListener('click', () => {
            var newUrl = `/my-account`;
            window.location.href = newUrl
        })
    }else {
        const openSignIn = document.querySelector('.sign-in')
        openSignIn.addEventListener('click', () => {
            accountPanel.style.display = 'flex'
            body.classList.add('no-scroll')
        })
    }
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

// Nút tăng giảm số lượng


const minus = document.querySelector('.minus')
const add = document.querySelector('.add')
const rect = document.querySelector('.rect')
const minusButton = document.querySelector('.modify-button-minus')
const plusButton = document.querySelector('.modify-button-plus')

minusButton.addEventListener('click', () => {
    var currentValue = document.getElementsByClassName("quantity-value")[0].value
    if(currentValue == 1) {
        minus.style.fill = "#E0E0E0"
        minusButton.disabled = true;
        plusButton.disabled = false;
    }else {
        currentValue--;
        minus.style.fill = "black"
        add.style.fill = "black"
        rect.style.fill = "black"
        minusButton.disabled = false;
        plusButton.disabled = false;
        document.querySelector('.quantity-value').value = currentValue--;
        if(document.querySelector('.quantity-value').value == 1) {
            minus.style.fill = "#E0E0E0"
            minusButton.disabled = true;
            plusButton.disabled = false;
        }
    }
})

plusButton.addEventListener('click', () => {
    var currentValue = document.getElementsByClassName("quantity-value")[0].value
    if(currentValue == 5) {
        add.style.fill = "#E0E0E0"
        rect.style.fill = "#E0E0E0"
        plusButton.disabled = true;
        // minusButton.disabled = false;
    }else {
        currentValue++;
        minus.style.fill = "black"
        add.style.fill = "black"
        rect.style.fill = "black"
        minusButton.disabled = false;
        document.querySelector('.quantity-value').value = currentValue++;
        if(document.querySelector('.quantity-value').value == 5) {
            add.style.fill = "#E0E0E0"
            rect.style.fill = "#E0E0E0"
            plusButton.disabled = true;
            // minusButton.disabled = false;
        }
    }
})

// Mở introductions, descriptions & Reviews

const toggleButton = document.querySelector('.product-accordion-toggle-button')
const toggleButtonRV = document.querySelector('.product-accordion-toggle-button-reviews')
const animate = document.querySelector('.animate-height-wrapper')
const animateRV = document.querySelector('.animate-height-wrapper-reviews')
const iconIntro = document.querySelector('.plus-minus-icon-intro')
const iconReview = document.querySelector('.plus-minus-icon-reviews')


toggleButton.addEventListener('click', () => {
    iconIntro.classList.toggle('activeRotate')
    animate.classList.toggle('show')
})


toggleButtonRV.addEventListener('click', () => {
    iconReview.classList.toggle('activeRotate')
    animateRV.classList.toggle('show')
})

// hiển thị % vote 

const totalReview = document.querySelector('.review-button')
const ratingBoxes = document.querySelectorAll('.rating-box')

document.addEventListener("DOMContentLoaded", () => {
    if(totalReview !== null) {
        const totalReviewValue = parseInt(totalReview.textContent)
        ratingBoxes.forEach((box) => {
            const rvValue =  parseInt(box.querySelector('.review-count').textContent.split(' ')[0])
            const fillValue = (rvValue / totalReviewValue) * 100
            if(fillValue == 0) {
                box.style.opacity = '0.5'
                box.style.fontStyle = 'italic'
                box.style.cursor = 'no-drop'
                box.querySelector('.fill').style.width = '0%'
            }else {
                box.querySelector('.fill').style.width = (fillValue * 112.00000000000001) / 100 + '%'
            }
        })
    }
})

// Chọn ảnh từ gallery thumbnail

const images = document.querySelectorAll('.gallery-thumbnails-img')
const imgSource = document.querySelector('.media-wrapper-img')
const counter = document.querySelector('.product-counter-galley')
const nextDetailBtn = document.querySelector('.control-button-next')
const prevDetailBtn = document.querySelector('.control-button-prev')

let currentImg = 0
images.forEach((img,index) => {
    img.addEventListener('click', () => {
        currentImg = index
        if(currentImg == 0) {
            prevDetailBtn.style.pointerEvents = 'none'
            prevDetailBtn.style.backgroundColor = '#a9a9a9'
        }else {
            prevDetailBtn.style.pointerEvents = 'auto'
            prevDetailBtn.style.backgroundColor = '#000000ab'
        }
        if(currentImg == images.length - 1) {
            nextDetailBtn.style.pointerEvents = 'none'
            nextDetailBtn.style.backgroundColor = '#a9a9a9'
        }else {
            nextDetailBtn.style.pointerEvents = 'auto'
            nextDetailBtn.style.backgroundColor = '#000000ab'
        }
        showImage(currentImg)
        counter.textContent = (currentImg + 1) + '/' + images.length
    })
})

function showImage(index) {
    imgSource.src = images[index].src
}

prevDetailBtn.addEventListener('click', () => {
    currentImg--;
    counter.textContent = (currentImg + 1) + '/' + images.length
    if(currentImg == 0) {
        prevDetailBtn.style.pointerEvents = 'none'
        prevDetailBtn.style.backgroundColor = '#a9a9a9'
    }else {
        prevDetailBtn.style.pointerEvents = 'auto'
        prevDetailBtn.style.backgroundColor = '#000000ab'
        nextDetailBtn.style.pointerEvents = 'auto'
        nextDetailBtn.style.backgroundColor = '#000000ab'
    }
    showImage(currentImg);
})

nextDetailBtn.addEventListener('click', () => {
    currentImg++;
    counter.textContent = (currentImg + 1) + '/' + images.length
    if(currentImg == images.length - 1) {
        nextDetailBtn.style.pointerEvents = 'none'
        nextDetailBtn.style.backgroundColor = '#a9a9a9'
    }else {
        prevDetailBtn.style.pointerEvents = 'auto'
        prevDetailBtn.style.backgroundColor = '#000000ab'
        nextDetailBtn.style.pointerEvents = 'auto'
        nextDetailBtn.style.backgroundColor = '#000000ab'
    }
    showImage(currentImg)
})

// Check điều kiện nội dung review

const textArea = document.querySelector('.text-area-review > textarea')
const requiredText = document.querySelector('.check-required')
const textAreaStatus = document.querySelector('.text-area-status')

textArea.addEventListener('input', () => {
    if(textArea.value.length < 50) {
        requiredText.textContent = "You must write at least 50 characters for this field."
        textAreaStatus.style.display = 'block'
        textAreaStatus.style.backgroundColor = 'rgb(208, 2, 27)'
    }else {
        requiredText.textContent = ""
        textAreaStatus.style.backgroundColor = 'rgb(0, 133, 55)'
    }
})

// Chọn file ảnh review

const fileButton = document.querySelector('.choose-image-button')
const fileInput = document.querySelector('.image-review-upload')

fileButton.addEventListener('click', (event) => {
    event.preventDefault()
    fileInput.click();
})

// Vote sao

const stars = document.querySelectorAll('.star')

stars.forEach((star) => {
    star.addEventListener('click', () => {
        const rating = star.getAttribute("data-rating")
        const ratingInput = document.querySelector("input[name='rating']");
        ratingInput.value = rating
        stars.forEach((s) => {
            s.style.color = '#d7d6d6'
        })
        for(let i = 1; i <= rating; i++) {
            stars[i - 1].style.color = '#ffd500'
        }
    })
})


// Hiển thị ảnh sau khi được chọn và xóa ảnh được chọn

const imagesFileInput = document.querySelector('.image-review-upload')
const imageWrapper = document.querySelector('.image-reviews')

imagesFileInput.addEventListener('change', (event) => {
    const files = event.target.files
    for(const file of files) {
        const li = document.createElement('li');
        const img = document.createElement('img');
        // const removeButton = document.createElement('button');
        
        img.src = URL.createObjectURL(file);
        // removeButton.classList.add('remove-image');
        // removeButton.innerHTML = `
        //     <svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" viewBox="0 0 17 17" aria-hidden="true" class="Icon__StyledSVG-lm07h6-0 kBQgfx" data-di-res-id="29d199db-3481e91" data-di-rand="1692711741192">
        //         <path d="M10.377 8.142l5.953-5.954-2.234-2.234-5.954 5.954L2.188-.046-.046 2.188l5.954 5.954-5.954 5.954 2.234 2.234 5.954-5.953 5.954 5.953 2.234-2.234z" fill="currentColor" fill-rule="evenodd"></path>
        //     </svg>
        // `;
        li.classList.add('image-preview-container');
        li.appendChild(img);
        // li.appendChild(removeButton);
        imageWrapper.appendChild(li);
    }
})

// document.addEventListener('DOMContentLoaded', () => {
//     const removeBtn = document.querySelectorAll('.remove-image');
//     removeBtn.forEach(button => {
//         button.addEventListener('click', () => {
//             const listItem = button.closest(".image-preview-container")
//             if(listItem) {
//                 listItem.remove();
//             }
//         });
//     });
// });

// review function

const likeButton = document.querySelectorAll('.rating-button-like');
const dislikeButton = document.querySelectorAll('.rating-button-dislike');
const likeSpans = document.querySelectorAll('.markup-counter-like')
const likeValues = []
likeSpans.forEach(span => {
    likeValues.push(parseInt(span.textContent))
})

const dislikeSpans = document.querySelectorAll('.markup-counter-dislike')
const dislikeValues = []
dislikeSpans.forEach(span => {
    dislikeValues.push(parseInt(span.textContent))
})


likeButton.forEach((button, index) => {
    button.addEventListener('click', () => {
        if(button.querySelector('path').getAttribute('fill') === '#CACACA') {
            button.querySelector('path').style.fill = '#006db7'
            button.querySelector('.markup-counter-like').textContent = parseInt(button.querySelector('.markup-counter-like').textContent) + 1
            const dislikeButton = button.closest('.product-review-container').querySelector('.rating-button-dislike')
            dislikeButton.querySelector('path').style.fill = '#CACACA'
            const decreaseValue = parseInt(dislikeButton.querySelector('.markup-counter-dislike').textContent) - 1
            if(dislikeValues[index] >= decreaseValue) {
                dislikeButton.querySelector('.markup-counter-dislike').textContent = dislikeValues[index]
            }else {
                dislikeButton.querySelector('.markup-counter-dislike').textContent = decreaseValue
            }
            button.disabled = true
            dislikeButton.disabled = false
        }
    })
})

dislikeButton.forEach((button, index) => {
    button.addEventListener('click', () => {
        if(button.querySelector('path').getAttribute('fill') === '#CACACA') {
            button.querySelector('path').style.fill = '#006db7'
            button.querySelector('.markup-counter-dislike').textContent = parseInt(button.querySelector('.markup-counter-dislike').textContent) + 1
            const likeButton = button.closest('.product-review-container').querySelector('.rating-button-like')
            likeButton.querySelector('path').style.fill = '#CACACA'
            const decreaseValue = parseInt(likeButton.querySelector('.markup-counter-like').textContent) - 1
            if(likeValues[index] >= decreaseValue) {
                likeButton.querySelector('.markup-counter-like').textContent = likeValues[index]
            }else {
                likeButton.querySelector('.markup-counter-like').textContent = decreaseValue
            }
            button.disabled = true
            likeButton.disabled = false
        }
    })
})

// Nút hiển thị thêm nội dung review

const readMoreButton = document.querySelectorAll('.read-more-button')
const reviewContent = document.querySelectorAll('.product-review-infor-lineclamp')

readMoreButton.forEach((button) => {
    button.addEventListener('click', () => {
        if(button.textContent === "Read more") {
            button.textContent = "Show less"
            button.closest('.product-review-container')
            .querySelector('.product-review-infor-lineclamp').style.maxHeight = "none"
        }else {
            button.textContent = "Read more"
            button.closest('.product-review-container')
            .querySelector('.product-review-infor-lineclamp').style.maxHeight = "7.5rem"
        }
    })
})

// Hiển thị thêm số lượng reviews

document.addEventListener("DOMContentLoaded", () => {
    const reviewItems = document.querySelectorAll('.product-review-container')

    if(reviewItems.length >= 5) {
        reviewItems.forEach(item => {
            item.style.display = 'none'
        })

        const loadMoreButton = document.querySelector('.load-more-review-button')
        const hideLessButton = document.querySelector('.hide-less-review-button')

        if(loadMoreButton !== null && hideLessButton !== null) {
            let currentQuantity = 0
            const rvPerPage = 5

            function showReview(index) {
                for(let i = index; i < index + rvPerPage; i++) {
                    if(reviewItems[i]) {
                        reviewItems[i].classList.add('active')
                    }
                }
                var contains = true
                for(let i = 0; i < reviewItems.length; i++) {
                    if(!reviewItems[i].classList.contains('active')) {
                        contains = false
                    }
                }
                if(contains == true) {
                    loadMoreButton.style.display = 'none'
                    hideLessButton.style.display = 'block'
                }
            }

            loadMoreButton.addEventListener('click', () => {
                currentQuantity += 5
                showReview(currentQuantity)
            })

            hideLessButton.addEventListener('click', () => {
                reviewItems.forEach((item) => {
                    item.classList.remove('active')
                })
                for(let i = 0; i < rvPerPage; i++) {
                    if(reviewItems[i]) {
                        reviewItems[i].classList.add('active')
                    }
                }
                loadMoreButton.style.display = 'block'
                hideLessButton.style.display = 'none'
                currentQuantity = 0
            })
            showReview(currentQuantity)
        }
    }
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

// hiện màu sao dựa vào trung bình vote

const avgRating = document.querySelector('.rating-bar-text')
const rvStars = document.querySelector('.review-rating-star')
const rvStarOverall = document.querySelector('.rating-container-detail')


document.addEventListener("DOMContentLoaded", () => {
    if(rvStars !== null && rvStarOverall !== null && avgRating !== null) {
        const avgValue = Math.floor(parseFloat(avgRating.textContent))
        const stars = rvStars.querySelectorAll("i")
        const starOverall = rvStarOverall.querySelectorAll("i")
        stars.forEach((star,index) => {
            if(index < avgValue) {
                star.style.color = "#ffd500"
            }else {
                star.style.color = "#cacaca"
            }
        })

        starOverall.forEach((star,index) => {
            if(index < avgValue) {
                star.style.color = "#ffd500"
            }else {
                star.style.color = "#cacaca"
            }
        })
    }
})

// đổi định dạng ngày tháng 

var dateSpan = document.querySelectorAll('.date')

document.addEventListener("DOMContentLoaded", () => {
    dateSpan.forEach((date) => {
        const dateConvert = new Date(date.textContent);
        const months = [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
        ];

        const day = dateConvert.getDate();
        const month = dateConvert.getMonth();
        const year = dateConvert.getFullYear();

        const daySuffix = "th";
        if (day === 1 || day === 21 || day === 31) {
        daySuffix = "st";
        } else if (day === 2 || day === 22) {
        daySuffix = "nd";
        } else if (day === 3 || day === 23) {
        daySuffix = "rd";
        }

        const formattedDate = months[month] + " " + day + daySuffix + ", " + year;
        date.textContent = formattedDate
    })
})

// hiện sao review chi tiết

const reviewDetailStars = document.querySelector('.review-detail-star')

document.addEventListener("DOMContentLoaded", () => {
    if(reviewDetailStars !== null) {
        const detailStars = reviewDetailStars.querySelectorAll("i")
        const reviewDetailValue = reviewDetailStars.querySelector('.rating-bar-text')
        const avgValue = Math.floor(parseFloat(reviewDetailValue.textContent))
        detailStars.forEach((star,index) => {
            if(index < avgValue) {
                star.style.color = "#ffd500"
            }else {
                star.style.color = "#cacaca"
            }
        })
    }
})