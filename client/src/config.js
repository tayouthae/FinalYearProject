export const CourseCreatorAddress = '0x5Dc6848dbc30884E4364d81fDF53416A5Db2c028';
export const CourseCreatorAbi = [{
        constant: true,
        inputs: [],
        name: 'getCreator',
        outputs: [{
            name: '',
            type: 'address',
        }, ],
        payable: false,
        stateMutability: 'view',
        type: 'function',
    },
    {
        constant: true,
        inputs: [],
        name: 'fetchCourseAddress',
        outputs: [{
            name: '',
            type: 'address',
        }, ],
        payable: false,
        stateMutability: 'view',
        type: 'function',
    },
    {
        constant: false,
        inputs: [{
            name: 'deadline',
            type: 'uint256',
        }, ],
        name: 'createCourse',
        outputs: [{
            name: '',
            type: 'address',
        }, ],
        payable: false,
        stateMutability: 'nonpayable',
        type: 'function',
    },
    {
        inputs: [],
        payable: false,
        stateMutability: 'nonpayable',
        type: 'constructor',
    },
];
export const CourseAbi = [{
        constant: true,
        inputs: [{
            name: '',
            type: 'address',
        }, ],
        name: 'fileHash',
        outputs: [{
            name: '',
            type: 'string',
        }, ],
        payable: false,
        stateMutability: 'view',
        type: 'function',
    },
    {
        constant: false,
        inputs: [{
            name: 'file',
            type: 'string',
        }, ],
        name: 'submit',
        outputs: [],
        payable: false,
        stateMutability: 'nonpayable',
        type: 'function',
    },
    {
        inputs: [{
            name: 'dl',
            type: 'uint256',
        }, ],
        payable: false,
        stateMutability: 'nonpayable',
        type: 'constructor',
    },
];