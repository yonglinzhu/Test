$(document).ready(function() {
    $('#addStudentForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    },
                    stringLength: {
                        min: 1,
                        max: 40,
                        message: '长度限制在40字内!'
                    }
                }
            },
            birthdayitem: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    }
                }
            },
            avgscore: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    },
                    stringLength: {
                        min: 1,
                        max: 255,
                        message: '长度限制在255字内!'
                    }
                }
            }
        }
    });
    
    
    $('#updateStudentForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    },
                    stringLength: {
                        min: 1,
                        max: 40,
                        message: '长度限制在40字内!'
                    }
                }
            },
            birthdayitem: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    }
                }
            },
            avgscore: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: '必填项!'
                    },
                    stringLength: {
                        min: 1,
                        max: 255,
                        message: '长度限制在255字内!'
                    }
                }
            }
        }
    });
});
