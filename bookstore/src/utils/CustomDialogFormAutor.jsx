import * as React from 'react';
import { useState } from "react";
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import { Redirect } from "react-router-dom";
import HOST from "../constants/host";
import check_expired from "../utils/useToken";

const BootstrapDialog = styled(Dialog)(({ theme }) => ({
    '& .MuiDialogContent-root': {
        padding: theme.spacing(2),
    },
    '& .MuiDialogActions-root': {
        padding: theme.spacing(1),
    },
}));


const BootstrapDialogTitle = (props) => {
    const { children, onClose, ...other } = props;

    return (
        <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
            {children}
            {onClose ? (
                <IconButton
                    aria-label="close"
                    onClick={onClose}
                    sx={{
                        position: 'absolute',
                        right: 8,
                        top: 8,
                        color: (theme) => theme.palette.grey[500],
                    }}
                >
                    <CloseIcon />
                </IconButton>
            ) : null}
        </DialogTitle>
    );
};

BootstrapDialogTitle.propTypes = {
    children: PropTypes.node,
    onClose: PropTypes.func.isRequired,
};

export default function CustomDialogFormAutor({
    open,
    handleClose,
    handleClickOpen,
}) {
    const [nume,setNume] = useState("");
    const [prenume,setPrenume] = useState("");
    var token = localStorage.getItem("token");

    function addAutor(nume, prenume) {
        if (!localStorage.getItem("token")) {
            return <Redirect to="/login" />;
        } else {
            if (check_expired()) {
                return <Redirect to="/login" />;
            }
        }

        fetch(HOST() + "/authors", {
            method: "POST",
            headers: {
                'Authorization': token,
                'Content-type': 'application/json',
            },
            body: JSON.stringify({ nume, prenume }),
        })
            .then((data) => {
                if (data.ok) {
                    data.json().then((message) => {
                        console.log(message);
                    });
                }
                else if (data.status === 404) {
                    console.log(data);
                }
                else if (data.status === 403) {
                    localStorage.removeItem("token");
                }
                else {
                    throw new Error("Internal server error");
                }
            })
            .catch((error) => {
                console.log(error);
            });
    }

    function clickOpen() {
        handleClickOpen();
    };

    function submit(nume,prenume)
    {
        addAutor(nume,prenume);
        handleClose();
        window.location.reload();
    };

    return (
        <div>
            <Button variant="outlined" onClick={clickOpen}>
                Add Autor
            </Button>
            <BootstrapDialog
                onClose={handleClose}
                aria-labelledby="customized-dialog-title"
                open={open}
            >
                <BootstrapDialogTitle id="customized-dialog-title" onClose={handleClose}>
                    Add Autor
                </BootstrapDialogTitle>
                <DialogContent dividers>
                <form>
                    <label>Nume:
                    <input
                        type="text" 
                        value={nume}
                        onChange={(e) => setNume(e.target.value)}
                    />
                    </label>
                    <label>Prenume:
                    <input
                        type="text" 
                        value={prenume}
                        onChange={(e) => setPrenume(e.target.value)}
                    />
                    </label>
                </form>
                </DialogContent>
                <Button onClick={()=>submit(nume,prenume)}>Add Autor</Button>
            </BootstrapDialog>
        </div>
    );
}
