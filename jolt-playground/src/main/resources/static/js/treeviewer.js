function createTreeView(json, parent) {
	const ul = document.createElement('ul');
	for (const key in json) {
		const li = document.createElement('li');
		li.innerHTML = `<span class="key">${key}</span>`;

		if (typeof json[key] === 'object' && json[key] !== null) {
			// Add expand/collapse functionality
			li.classList.add('collapsed');
			const childUl = createTreeView(json[key], li);
			childUl.classList.add('hidden');
			li.appendChild(childUl);

			li.addEventListener('click', (e) => {
				e.stopPropagation(); // Prevent bubbling
				li.classList.toggle('collapsed');
				li.classList.toggle('expanded');
				childUl.classList.toggle('hidden');
			});
		} else {
			li.innerHTML += `: <span class="value">${json[key]}</span>`;
		}
		ul.appendChild(li);
	}
	return ul;
}

function generateTree(input, target,targetFocusElement) {
	// const input = document.getElementById('jsonInput').value;
	const treeContainer = document.getElementById(target);

	// Clear the previous tree
	treeContainer.innerHTML = '';

	try {
		const json = JSON.parse(input);
		const tree = createTreeView(json, treeContainer);
		treeContainer.appendChild(tree);
		window.switchTab(targetFocusElement);
	} catch (e) {
		alert('Invalid JSON! Please provide a valid JSON input.');
	}
}