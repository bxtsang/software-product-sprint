// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

window.onload = function() {
  setTimeout(toggleContent, 3500);
}

/**
 * Generate a random fact about myself
 */
function randomFact() {
  const facts = [
    'I can play the guitar!', 
    'I broke my thumb before...',
    'My hair turned brown from swimming (chlorine).',
    'I love singing.',
    'I am a Hong Kong PR.'
  ];
  var fact = facts[Math.floor(Math.random() * facts.length)];

  document.querySelector('#fact').innerText = fact;
}

/**
 * Hides the intro-wrapper and displays the main content
 */
function toggleContent() {
  document.querySelector('#intro-wrapper').classList.add('hide');
  document.querySelector('#content').classList.remove('hide');
}

/**
 * Toggles the display of water polo images
 */
function toggleImages() {
  var images = document.querySelectorAll('.wp-img');

  if (images[0].classList.contains('hide')) {
    images.forEach(image => {
      image.classList.remove('hide');
    });
  } else {
    images.forEach(image => {
      image.classList.add('hide');
    });
  }
}