import {getUsername, setUsername} from "../src/js/dchat-username.js";

describe("dchat-username.js:", function() {

    beforeEach(() => {
        localStorage.clear();
    });

    it("default username is ''", function() {
        expect(getUsername()).toBe("");
    });

    it("set username can be retrieved", function() {
        setUsername("Francis");
        expect(getUsername()).toBe("Francis");
    });
});